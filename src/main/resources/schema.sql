create table COLLATERAL_TYPE (	
  id IDENTITY primary key,
  type_name VARCHAR2(10),
  min_year_of_issue YEAR,
  min_assessed_date DATE,
  min_assessed_value DECIMAL(20, 2)
);

create table COLLATERAL (	
  id IDENTITY primary key,
  collateral_type bigint REFERENCES collateral_type(id),
  year_of_issue YEAR
);

create table ASSESS (	
  id IDENTITY primary key,
  collateral bigint REFERENCES collateral(id),
  assessed_date DATE,
  assessed_value DECIMAL(20, 2)
);