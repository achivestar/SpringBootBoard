CREATE TABLE `tb_board` (
	`idx` INT(11) NOT NULL AUTO_INCREMENT COMMENT '글번호(PK)',
	`title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '작성자' COLLATE 'utf8_general_ci',
	`content` TEXT NOT NULL COMMENT '내용' COLLATE 'utf8_general_ci',
	`writer` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '작성자' COLLATE 'utf8_general_ci',
	`view_cnt` INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	`noticeYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '공지글 여부' COLLATE 'utf8_general_ci',
	`secretYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '비밀글 여부' COLLATE 'utf8_general_ci',
	`deleteYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '삭제 여부' COLLATE 'utf8_general_ci',
	`insertTime` DATETIME NULL DEFAULT NULL COMMENT '등록일',
	`updateTime` DATETIME NULL DEFAULT NULL COMMENT '수정일',
	`deleteTime` DATETIME NULL DEFAULT NULL COMMENT '삭제일',
	PRIMARY KEY (`idx`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
