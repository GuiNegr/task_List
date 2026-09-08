create TYPE logType as ENUM('SUCCESSFUL','ERROR','WARNING');

alter table log add column log_type logType;