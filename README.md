# springbootApp

open the project in Intellij ide
At the first time before running the project create mysql database with name salaj_app.
for first time open the SalajWebAppApplication.java file , right click and run it and for next time server startup option will be configured automatically.




Please Run these below Query before start the application.

create table state (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL,
 PRIMARY KEY (`id`)
);

create table restaurant (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(254) NOT NULL,
 `city_id` bigint(20) NOT NULL,
 `state_id` bigint(20) NOT NULL,
 `address` varchar(254) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `FK456321NM123` (`city_id`),
 KEY `FK456321NM124` (`state_id`),
 CONSTRAINT `FK456321NM123` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
 CONSTRAINT `FK456321NM124` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
);

create table booked_order (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `user_id` bigint(20) NOT NULL,
 `restaurant_id` bigint(20) NOT NULL,
 `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `no_of_guests` int(2) NOT NULL,
 PRIMARY KEY (`id`),
 KEY `FK456321NM125` (`user_id`),
 KEY `FK456321NM126` (`restaurant_id`),
 CONSTRAINT `FK456321NM125` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
 CONSTRAINT `FK456321NM126` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);



now run the project.
you will have username having email : test@test.com in database
as well as 10 city name in city table as seeding data
And 27 states name state table.




test the api on post man.
Urls to test:
1.) http://localhost:8080/users
2.) http://localhost:8080/users/login
Options : GET,POST,PUT
