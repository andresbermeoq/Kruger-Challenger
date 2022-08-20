INSERT INTO public.kruger_role(id_role, role_description, role_name)VALUES(1, 'Role for the admin users.', 'ADMIN');
INSERT INTO public.kruger_role(id_role, role_description, role_name)VALUES(2, 'Role for the employee users.', 'USER');

INSERT INTO public.kruger_user(id_user, user_password, user_username, user_role)VALUES(1,'$2a$12$KMV4MsjLNqwjH0tOzgIVNOx3RJ2fv4xDiWsRDLA8b3E3v368ZoibW', 'aBermeo', 1);
