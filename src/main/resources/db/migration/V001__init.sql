CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE age_range AS ENUM ('baby', 'toddler', 'kid', 'teenager', 'adult');
CREATE TYPE gender as ENUM('male', 'female', 'neutral', 'undefined');
CREATE TYPE persons_type as ENUM('adult', 'minor');
CREATE TYPE offering_type as ENUM('hosting', 'driving');
CREATE TYPE bed_type as ENUM('crib', 'single', 'double', 'single_couch', 'double_couch');

CREATE TABLE languages(
    code CHAR(2) PRIMARY KEY NOT NULL,
    label VARCHAR(64)
);

CREATE TABLE persons (
    id UUID DEFAULT uuid_generate_v4(),
    age_range age_range,
    type persons_type,
    gender gender,
    age SMALLINT,
    email VARCHAR(128) UNIQUE,
    first_name VARCHAR(32),
    middle_name VARCHAR(32),
    last_name VARCHAR(32),
    telephone_number VARCHAR(16),
    PRIMARY KEY(id)
);

CREATE TABLE persons_languages (
    persons_id UUID NOT NULL,
    languages_id CHAR(2) NOT NULL,

    FOREIGN KEY (persons_id) REFERENCES persons(id),
    FOREIGN KEY (languages_id) REFERENCES languages(code)
);

CREATE TABLE offerings (
    id UUID DEFAULT uuid_generate_v4(),
    from_timestamp TIMESTAMP,
    to_timestamp TIMESTAMP,
    type OFFERING_TYPE NOT NULL,
    space_id UUID,
    driving_id UUID,
    PRIMARY KEY (id)
);

CREATE TABLE spaces (
    id UUID DEFAULT uuid_generate_v4(),
    PRIMARY KEY (id)
);

CREATE TABLE beds (
    id UUID DEFAULT uuid_generate_v4(),
    type bed_type NOT NULL,
    PRIMARY KEY (id)
);