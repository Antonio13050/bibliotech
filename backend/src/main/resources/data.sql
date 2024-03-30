INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC')
ON CONFLICT (role_id) DO NOTHING;