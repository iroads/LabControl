insert into unit (name) values ('Давлекановское ДРСУ');
insert into unit (name) values ('Белорецкое ДРСУ');
insert into unit (name) values ('Уфимское ДРСУ');

insert into users (name, surname, unit_id) values ('Иван', 'Жуковский', 1);
insert into users (name, surname, unit_id) values ('Александр', 'Курцебо', 1);
insert into users (name, surname, unit_id) values ('Андрей', 'Попадейкин', 2);
insert into users (name, surname, unit_id) values ('Тимур', 'Абсалямов', 3);

insert into plant (model, location, productivity, unit_id) values ('Lintec CSM-3000', 'с.Бураево', 240, 1);
insert into plant (model, location, productivity, unit_id) values ('Lintec CSM-3000', 'д.Кадыш', 240, 2);
insert into plant (model, location, productivity, unit_id) values ('Ammann Global 160', 'г.Уфа', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'г.Стерлитамак', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'д.Душанбеково', 160, 1);
insert into plant (model, location, productivity, unit_id) values ('TTM GLB-2000', 'г.Баймак', 160, 3);
