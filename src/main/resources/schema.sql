CREATE TABLE waste_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    disposalGuidelines VARCHAR(1000),
    recyclingTips VARCHAR(1000)
);
