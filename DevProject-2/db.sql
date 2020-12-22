CREATE TABLE `tb_board` (
	`idx` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�۹�ȣ(PK)',
	`title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '�ۼ���' COLLATE 'utf8_general_ci',
	`content` TEXT NOT NULL COMMENT '����' COLLATE 'utf8_general_ci',
	`writer` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '�ۼ���' COLLATE 'utf8_general_ci',
	`view_cnt` INT(11) NOT NULL DEFAULT '0' COMMENT '��ȸ��',
	`noticeYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '������ ����' COLLATE 'utf8_general_ci',
	`secretYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '��б� ����' COLLATE 'utf8_general_ci',
	`deleteYn` ENUM('Y','N') NOT NULL DEFAULT 'N' COMMENT '���� ����' COLLATE 'utf8_general_ci',
	`insertTime` DATETIME NULL DEFAULT NULL COMMENT '�����',
	`updateTime` DATETIME NULL DEFAULT NULL COMMENT '������',
	`deleteTime` DATETIME NULL DEFAULT NULL COMMENT '������',
	PRIMARY KEY (`idx`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
