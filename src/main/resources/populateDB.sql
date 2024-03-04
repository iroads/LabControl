insert into unit (name) values ('Давлекановское ДРСУ');
insert into unit (name) values ('Белорецкое ДРСУ');
insert into unit (name) values ('Уфимское ДРСУ');

insert into users (name, surname, position, login, password, roles, unit_id)
values ('Иван', 'Жуковский','Зам. главного инженера', 'admin', '$2a$10$Hzs4TdoBfYDZZqBqOzadFOHsuM9spByfJaidcei/.Tiqk95kEueRK', 'ROLE_ADMIN', null);
-- $2a$10$Hzs4TdoBfYDZZqBqOzadFOHsuM9spByfJaidcei/.Tiqk95kEueRK <--Данная запись эквивалентна паролю admin зашифрованному через Bcrypt


insert into plant (model, location, productivity, unit_id) values ('Lintec CSM-3000', 'с.Бураево', 240, 1);
insert into plant (model, location, productivity, unit_id) values ('Lintec CSM-3000', 'д.Кадыш', 240, 2);
insert into plant (model, location, productivity, unit_id) values ('Ammann Global 160', 'г.Уфа', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'г.Стерлитамак', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'д.Душанбеково', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'г.Баймак', 160, 3);


insert into hot_mix_test_result (chog315, chog224, chog16, chog112, chog8, chog56, chog4, chog2, chog1, chog05, chog025, chog0125, chog0063, dno, gmb, gmm)
values (0,0,106,408,410,0,372,288,198,124,80,42,50,141, 2.455, 2.599);
delete from hot_mix_test_result where id = 3;

insert into batch (quantity) values (250);
insert into batch (quantity) values (270);
insert into batch (quantity) values (1000.45);

