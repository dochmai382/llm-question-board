# CREATE TABLE users (
#     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
#     username VARCHAR(50) NOT NULL UNIQUE,
#     password VARCHAR(100) NOT NULL,
#     nickname VARCHAR(50) NOT NULL,
#     role VARCHAR(20) DEFAULT 'USER',
#     enabled BOOLEAN DEFAULT TRUE
# );

CREATE TABLE users (
   user_id VARCHAR(36) PRIMARY KEY,
   username VARCHAR(50) NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   nickname VARCHAR(50) NOT NULL,
   role VARCHAR(20) DEFAULT 'USER',
   enabled BOOLEAN DEFAULT TRUE,   -- 계정 활성화 여부
   points INT DEFAULT 0,           -- 유저 포인트 (기본값 0)
   report_count INT DEFAULT 0,     -- 리포트 카운트 (기본값 0)
   is_banned BOOLEAN DEFAULT FALSE -- 유저가 밴된 상태인지 여부
);