create TYPE task_status as ENUM('PENDING',
    'COMPLETED',
    'IN_PROGRESS',
    'IN_WAITING',
    'NEW');

alter table task add column taskStatus task_status;