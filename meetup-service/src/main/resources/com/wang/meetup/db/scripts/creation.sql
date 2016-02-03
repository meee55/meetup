DROP TABLE IF EXISTS OrganizationCategory;
DROP TABLE IF EXISTS Organizations;
DROP TABLE IF EXISTS Categories;

create table Categories(
	id int(10) not null primary key auto_increment,
	name varchar(250) not null UNIQUE comment '分类名称'
)comment '组织分类表';

create table Organizations (
	id int(10) not null primary key auto_increment,
	name varchar(250) not null UNIQUE comment '机构名称',
	slogan varchar(250) comment '标语',
	banner varchar(250) comment '横幅',
	creationDate Datetime not null default now() comment '建立时间'
) comment '组织表';

create table OrganizationCategory (
	categoryId int(10) not null comment '分类',
	organizationId int(10) not null comment '组织',
	CONSTRAINT FOREIGN KEY (categoryId) REFERENCES Categories(id),
	CONSTRAINT FOREIGN KEY (organizationId) REFERENCES Organizations(id),
	CONSTRAINT organization_category_unique UNIQUE (categoryId, organizationId)
) comment '组织分类关联表';