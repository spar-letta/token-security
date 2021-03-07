INSERT INTO `user` (`id`, `name`, `password`, `username`) VALUES
(1, 'enock', '$2a$10$OBCwavvK/A8TDIMazGcsYeJWSIF2/ySynLuVT.i3nLiaY0zc1AYWy', 'jav');

INSERT INTO `role` (`id`, `name`) VALUES(1, 'ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES(2, 'USER');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES(1, 1);

