CREATE TABLE `products` (
  `barcode` varchar(20) NOT NULL COMMENT '管理番号（バーコード）',
  `name` varchar(100) NOT NULL COMMENT '商品名',
  `cost_price` decimal(10,2) NOT NULL COMMENT '仕入れ値',
  `sale_price` decimal(10,2) NOT NULL COMMENT '売値',
  UNIQUE KEY `products_IX1` (`barcode`)