CREATE TABLE users(
	u_id serial PRIMARY KEY,
	username text,
	password text);	
	
CREATE TABLE goals(
	g_id serial PRIMARY KEY,
	title text,
	parent integer REFERENCES goals);
	
CREATE TABLE tasks(
	t_id serial PRIMARY KEY,
	title text, 
	description text,
	username text, 
	task_date date, 
	is_done boolean, 
	goal_id integer, 
	FOREIGN KEY (goal_id) REFERENCES goals(g_id));
	
INSERT INTO users (title, password)
	VALUES ('Nata', '1');

INSERT INTO goals (title) 
	VALUES ('goal1');
	
INSERT INTO goals (title, parent) 
	VALUES ('goal2', 1);

INSERT INTO tasks(title, description, username, task_date, is_done, goal_id)
VALUES('task1','do task1','Nata','2020-07-07', false, 2);

