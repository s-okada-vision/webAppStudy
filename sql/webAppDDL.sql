DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- ユーザーID (主キー)
    name VARCHAR(100) NOT NULL,         -- 名前 (100文字まで)
    email VARCHAR(100) UNIQUE,          -- メールアドレス (一意)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 作成日時
);

CREATE TABLE `webdbproducts` (
  `barcode` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '管理番号（バーコード）',
  `name` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '商品名',
  `cost_price` decimal(10,2) NOT NULL COMMENT '仕入れ値',
  `sale_price` decimal(10,2) NOT NULL COMMENT '売値',
  PRIMARY KEY (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin

CREATE TABLE `m_usr` (
  `USER_ID` varchar(20) NOT NULL COMMENT 'ユーザーID',
  `USER_PASSWORD` varchar(50) NOT NULL COMMENT 'パスワード',
  `INSERT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci