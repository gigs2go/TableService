insert into companies (id, name) values (1, 'ensignos');

insert into groups (id, name, company_id) values (1,'Executive',1);
insert into groups (id, name, company_id) values (2,'Sales: Managers',1);
insert into groups (id, name, company_id) values (3,'Sales: Handler',1);
insert into groups (id, name, company_id) values (4,'Sales: Admin',1);
insert into groups (id, name, company_id) values (5,'Operations: Facilities',1);
insert into groups (id, name, company_id) values (6,'Operations: Utilities',1);


insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (1,'tim@test.com','password','tim','TEST',1,1,1);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (2,'paddy@test.com','password','paddy','TEST',1,1,1);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (3,'admin@test.com','password','admin','ADMIN',1,1,1);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (4,'mobile@test.com','password','mobile','MOBILE',1,1,1);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (5,'desktop@test.com','password','desktop','DESKTOP',1,1,1);

insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (6,'user1@ensignos.com','password','user1','MOBILE',1,1,1);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (7,'user2@ensignos.com','password','user2','MOBILE',1,1,2);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (8,'user3@ensignos.com','password','user3','MOBILE',1,1,3);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (9,'user4@ensignos.com','password','user4','MOBILE',1,1,4);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (10,'user5@ensignos.com','password','user5','MOBILE',1,1,5);
insert into users (id,username,password,userKey,userType,enabled,company_id,group_id) values (11,'user6@ensignos.com','password','user6','MOBILE',1,1,6);

insert into authorities (id,username,authority,user_id) values(1,'tim@test.com','ROLE_ADMIN',1);
insert into authorities (id,username,authority,user_id) values(2,'tim@test.com','ROLE_TEST',1);
insert into authorities (id,username,authority,user_id) values(3,'tim@test.com','ROLE_MOBILE',1);
insert into authorities (id,username,authority,user_id) values(4,'tim@test.com','ROLE_DESKTOP',1);
insert into authorities (id,username,authority,user_id) values(5,'paddy@test.com','ROLE_ADMIN',2);
insert into authorities (id,username,authority,user_id) values(6,'paddy@test.com','ROLE_TEST',2);
insert into authorities (id,username,authority,user_id) values(7,'paddy@test.com','ROLE_MOBILE',2);
insert into authorities (id,username,authority,user_id) values(8,'paddy@test.com','ROLE_DESKTOP',2);
insert into authorities (id,username,authority,user_id) values(9,'admin@test.com','ROLE_ADMIN',3);
insert into authorities (id,username,authority,user_id) values(10,'admin@test.com','ROLE_MOBILE',3);
insert into authorities (id,username,authority,user_id) values(11,'admin@test.com','ROLE_DESKTOP',3);
insert into authorities (id,username,authority,user_id) values(12,'mobile@test.com','ROLE_MOBILE',4);
insert into authorities (id,username,authority,user_id) values(13,'desktop@test.com','ROLE_DESKTOP',5);

insert into authorities (id,username,authority,user_id) values(14,'user1@ensignos.com','ROLE_MOBILE',6);
insert into authorities (id,username,authority,user_id) values(15,'user2@ensignos.com','ROLE_MOBILE',7);
insert into authorities (id,username,authority,user_id) values(16,'user3@ensignos.com','ROLE_MOBILE',8);
insert into authorities (id,username,authority,user_id) values(17,'user4@ensignos.com','ROLE_MOBILE',9);
insert into authorities (id,username,authority,user_id) values(18,'user5@ensignos.com','ROLE_MOBILE',10);
insert into authorities (id,username,authority,user_id) values(19,'user6@ensignos.com','ROLE_MOBILE',11);

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(1,'folder','Executive',0,0,2,NULL,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(2,'folder','Sales',0,0,2,NULL,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(3,'folder','Operations',0,0,2,NULL,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(4,'folder','Finance',0,0,2,NULL,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(5,'folder','Leads',0,0,2,2,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(6,'folder','Potentials',0,0,2,2,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(7,'folder','Customers',0,0,2,2,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(8,'folder','Facilities',0,0,2,3,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(9,'folder','Utilities',0,0,2,3,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(10,'folder','Accounts Payable',0,0,2,4,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(11,'folder','Accounts Receivable',0,0,2,4,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(12,'folder','Gas',0,0,2,9,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(13,'folder','Water',0,0,2,9,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(14,'folder','Electricity',0,0,2,9,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(15,'folder','Suppliers',0,0,2,10,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(16,'folder','Contractors',0,0,2,10,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(17,'folder','Customers',0,0,2,11,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(18,'folder','Channels',0,0,2,11,'2012-09-02');

insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(19,'folder','Facilities',0,0,2,15,'2012-09-02');
insert into items (id, entryType, name, unshared, chartCount, owner_id, parent_id, publishedDate) values(20,'folder','Utilities',0,0,2,15,'2012-09-02');

insert into folders_items(folder_id, item_id, folder_order) values(2,5,0);
insert into folders_items(folder_id, item_id, folder_order) values(2,6,1);
insert into folders_items(folder_id, item_id, folder_order) values(2,7,2);

insert into folders_items(folder_id, item_id, folder_order) values(3,8,0);
insert into folders_items(folder_id, item_id, folder_order) values(3,9,1);

insert into folders_items(folder_id, item_id, folder_order) values(4,10,0);
insert into folders_items(folder_id, item_id, folder_order) values(4,11,1);

insert into folders_items(folder_id, item_id, folder_order) values(9,12,0);
insert into folders_items(folder_id, item_id, folder_order) values(9,13,1);
insert into folders_items(folder_id, item_id, folder_order) values(9,14,2);

insert into folders_items(folder_id, item_id, folder_order) values(10,15,0);
insert into folders_items(folder_id, item_id, folder_order) values(10,16,1);

insert into folders_items(folder_id, item_id, folder_order) values(11,17,0);
insert into folders_items(folder_id, item_id, folder_order) values(11,18,1);

insert into folders_items(folder_id, item_id, folder_order) values(15,19,0);
insert into folders_items(folder_id, item_id, folder_order) values(15,20,1);

insert into folders_groups (folder_id,group_id) values (1,1);

insert into folders_groups (folder_id,group_id) values (2,1);
insert into folders_groups (folder_id,group_id) values (2,2);
insert into folders_groups (folder_id,group_id) values (2,3);
insert into folders_groups (folder_id,group_id) values (2,4);

insert into folders_groups (folder_id,group_id) values (3,1);
insert into folders_groups (folder_id,group_id) values (3,5);
insert into folders_groups (folder_id,group_id) values (3,6);

insert into folders_groups (folder_id,group_id) values (4,1);
insert into folders_groups (folder_id,group_id) values (4,2);
insert into folders_groups (folder_id,group_id) values (4,4);
insert into folders_groups (folder_id,group_id) values (4,5);
insert into folders_groups (folder_id,group_id) values (4,6);

insert into folders_groups (folder_id,group_id) values (5,1);
insert into folders_groups (folder_id,group_id) values (5,2);
insert into folders_groups (folder_id,group_id) values (5,3);

insert into folders_groups (folder_id,group_id) values (6,1);
insert into folders_groups (folder_id,group_id) values (6,2);
insert into folders_groups (folder_id,group_id) values (6,3);

insert into folders_groups (folder_id,group_id) values (7,1);
insert into folders_groups (folder_id,group_id) values (7,2);
insert into folders_groups (folder_id,group_id) values (7,3);
insert into folders_groups (folder_id,group_id) values (7,4);

insert into folders_groups (folder_id,group_id) values (8,1);
insert into folders_groups (folder_id,group_id) values (8,5);

insert into folders_groups (folder_id,group_id) values (9,1);
insert into folders_groups (folder_id,group_id) values (9,6);

insert into folders_groups (folder_id,group_id) values (10,1);
insert into folders_groups (folder_id,group_id) values (10,5);
insert into folders_groups (folder_id,group_id) values (10,6);

insert into folders_groups (folder_id,group_id) values (11,1);
insert into folders_groups (folder_id,group_id) values (11,2);
insert into folders_groups (folder_id,group_id) values (11,4);

insert into folders_groups (folder_id,group_id) values (12,1);
insert into folders_groups (folder_id,group_id) values (12,6);

insert into folders_groups (folder_id,group_id) values (13,1);
insert into folders_groups (folder_id,group_id) values (13,6);

insert into folders_groups (folder_id,group_id) values (14,1);
insert into folders_groups (folder_id,group_id) values (14,6);

insert into folders_groups (folder_id,group_id) values (15,1);
insert into folders_groups (folder_id,group_id) values (15,5);
insert into folders_groups (folder_id,group_id) values (15,6);

insert into folders_groups (folder_id,group_id) values (16,1);

insert into folders_groups (folder_id,group_id) values (17,1);
insert into folders_groups (folder_id,group_id) values (17,2);
insert into folders_groups (folder_id,group_id) values (17,4);

insert into folders_groups (folder_id,group_id) values (18,1);
insert into folders_groups (folder_id,group_id) values (18,2);
insert into folders_groups (folder_id,group_id) values (18,4);

insert into folders_groups (folder_id,group_id) values (19,1);
insert into folders_groups (folder_id,group_id) values (19,5);

insert into folders_groups (folder_id,group_id) values (20,1);
insert into folders_groups (folder_id,group_id) values (20,6);
