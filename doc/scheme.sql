create user ecm with encrypted password '!@Hy98657020';
create database bitflow_ecm owner ecm encoding 'utf-8';
-- lc_collate = 'en_US.UTF-8' lc_ctype = 'en_US.UTF-8' template template0;
grant all privileges on database bitflow_ecm to ecm;
grant select, insert, update, delete on all tables in schema public to ecm;

