CREATE TABLE `agenda` (
  `subject` VARCHAR(200) NOT NULL,
  `result` VARCHAR(10) NOT NULL,
  `creation_datetime` DATETIME NOT NULL,
  PRIMARY KEY (`subject`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `associate` (
  `cpf` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vote` (
  `id` INT NOT NULL,
  `cpf` INT NOT NULL,
  `creation_datetime` DATETIME NULL DEFAULT current_timestamp,
  `choice` CHAR(3) NOT NULL,
  `subject` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`,`subject`, `cpf`),
  CONSTRAINT `fk_vote_associate1`
    FOREIGN KEY (`cpf`)
    REFERENCES `associate` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_agenda1`
    FOREIGN KEY (`subject`)
    REFERENCES `agenda` (`subject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `session` (
  `id` INT NOT NULL,
  `open_time` INT NULL,
  `creation_datetime` DATETIME NULL,
  `end_datetime` DATETIME NULL,
  `status` VARCHAR(7) NULL,
  `subject` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`, `subject`),
  CONSTRAINT `fk_session_agenda1`
    FOREIGN KEY (`subject`)
    REFERENCES `agenda` (`subject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX `idx_subject` ON `agenda` (`subject` ASC, `creation_datetime` DESC);

CREATE INDEX `fk_vote_associate_idx` ON `vote` (`cpf` ASC);

CREATE INDEX `idx_opened` ON `session` (`status` DESC);

CREATE INDEX `fk_session_agenda_idx` ON `session` (`subject` ASC);