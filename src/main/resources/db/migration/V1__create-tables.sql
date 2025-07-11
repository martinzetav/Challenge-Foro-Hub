-- Tabla: courses
CREATE TABLE courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255)
);

-- Tabla: profiles
CREATE TABLE profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Tabla: users
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tabla intermedia: user_profile (ManyToMany)
CREATE TABLE user_profile (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    CONSTRAINT fk_userprofile_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_userprofile_profile FOREIGN KEY (profile_id) REFERENCES profiles(id)
);

-- Tabla: topics
CREATE TABLE topics (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT,
    creation_date DATETIME,
    status VARCHAR(50),
    author_id BIGINT,
    course_id BIGINT,
    CONSTRAINT fk_topic_author FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_topic_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Tabla: responses
CREATE TABLE responses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message TEXT NOT NULL,
    topic_id BIGINT,
    creation_date DATETIME,
    author_id BIGINT,
    solution VARCHAR(255),
    CONSTRAINT fk_response_topic FOREIGN KEY (topic_id) REFERENCES topics(id),
    CONSTRAINT fk_response_author FOREIGN KEY (author_id) REFERENCES users(id)
);
