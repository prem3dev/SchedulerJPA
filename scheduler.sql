CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           title VARCHAR(255) NOT NULL,
                           task LONGTEXT,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          contents VARCHAR(255),
                          user_id BIGINT NOT NULL,
                          schedule_id BIGINT NOT NULL,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE
);
