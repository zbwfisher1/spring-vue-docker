

CREATE TABLE `userScore` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object` varchar(255) NOT NULL DEFAULT '',
  `score` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 1	张三	语文	81
-- 2	张三	数学	78
-- 3	李四	语文	88
-- 4	李四	数学	25
-- 5	王五	语文	87
-- 6	王五	数学	88
-- 7	王五	英文	100

SELECT S.name
FROM userScore S
GROUP BY S.name
Having MIN(S.score)>=20 and MAX(S.score)<100
