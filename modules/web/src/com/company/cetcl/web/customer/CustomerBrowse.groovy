package com.company.cetcl.web.customer

import com.company.cetcl.entity.Customer
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.HBoxLayout
import com.haulmont.cuba.gui.components.LinkButton
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory

import javax.inject.Inject

public class CustomerBrowse extends AbstractLookup {

    @Inject
    Table<Customer> customersTable

    @Inject
    ComponentsFactory componentsFactory

    @Override
    void init(Map<String, Object> params) {

        customersTable.addGeneratedColumn("orders", new Table.ColumnGenerator<Customer>() {
            @Override
            Component generateCell(Customer entity) {

                def box = componentsFactory.createComponent(HBoxLayout)

                entity.orders.each {
                    def linkButton = componentsFactory.createComponent(LinkButton)
                    linkButton.caption = it.orderDate
                    if (it.orderDate < new Date()) {
                        linkButton.icon = 'font-icon:WARNING'
                        linkButton.styleName = 'warn'
                    }
                    linkButton.action = new BaseAction(it.id.toString()) {
                        @Override
                        void actionPerform(Component component) {
                            openEditor(it, WindowManager.OpenType.DIALOG)
                        }
                    }
                    box.add(linkButton)
                }
                box
            }
        })
    }
}