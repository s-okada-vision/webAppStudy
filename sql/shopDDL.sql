CREATE TABLE products (
  `barcode` long(20) NOT NULL COMMENT '管理番号（バーコード）',
  `name` string(100) NOT NULL COMMENT '商品名',
  `cost_price` int(10,2) NOT NULL COMMENT '仕入れ値',
  `sale_price` int(10,2) NOT NULL COMMENT '売値',
  UNIQUE KEY `products_IX1` (`barcode`)