-- begin CETCL_ORDER
alter table CETCL_ORDER add constraint FK_CETCL_ORDER_CUSTOMER foreign key (CUSTOMER_ID) references CETCL_CUSTOMER(ID)^
create index IDX_CETCL_ORDER_CUSTOMER on CETCL_ORDER (CUSTOMER_ID)^
-- end CETCL_ORDER
