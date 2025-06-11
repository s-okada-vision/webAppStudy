CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- ユーザーID (主キー)
    name VARCHAR(100) NOT NULL,         -- 名前 (100文字まで)
    email VARCHAR(100) UNIQUE,          -- メールアドレス (一意)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 作成日時
);