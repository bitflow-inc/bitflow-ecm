create user ecm with encrypted password '!@Hy98657020';
grant all privileges on database bitflow_ecm to ecm;
grant select, insert, update, delete on all tables in schema public to ecm;