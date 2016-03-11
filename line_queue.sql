--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: insert_into_modified_column(); Type: FUNCTION; Schema: public; Owner: Guest
--

CREATE FUNCTION insert_into_modified_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.modified = now();
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.insert_into_modified_column() OWNER TO "Guest";

--
-- Name: update_modified_column(); Type: FUNCTION; Schema: public; Owner: Guest
--

CREATE FUNCTION update_modified_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.modified = now();
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_modified_column() OWNER TO "Guest";

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: check_ins; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE check_ins (
    id integer NOT NULL,
    restaurant_id integer,
    user_id integer,
    line_length integer,
    modified timestamp without time zone
);


ALTER TABLE check_ins OWNER TO "Guest";

--
-- Name: check_ins_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE check_ins_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE check_ins_id_seq OWNER TO "Guest";

--
-- Name: check_ins_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE check_ins_id_seq OWNED BY check_ins.id;


--
-- Name: restaurants; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE restaurants (
    id integer NOT NULL,
    restaurant_name character varying,
    phone character varying,
    street character varying,
    city character varying,
    state character varying,
    zip character varying,
    line_average integer,
    photo character varying
);


ALTER TABLE restaurants OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE restaurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_id_seq OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE restaurants_id_seq OWNED BY restaurants.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    user_name character varying,
    score integer,
    password character varying
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY check_ins ALTER COLUMN id SET DEFAULT nextval('check_ins_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: check_ins; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY check_ins (id, restaurant_id, user_id, line_length, modified) FROM stdin;
3	1	5	7	2016-03-10 16:08:53.851149
4	1	4	14	2016-03-10 16:16:09.335471
5	1	4	11	2016-03-10 16:23:47.786406
6	1	4	9	2016-03-10 17:00:45.401117
7	1	4	15	2016-03-10 17:13:37.978377
\.


--
-- Name: check_ins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('check_ins_id_seq', 7, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurants (id, restaurant_name, phone, street, city, state, zip, line_average, photo) FROM stdin;
2	restaurant	1111111111	11 street	portland	or	97204	3	\N
3	Food	2222222	Main St.	Portland	OR	97201	\N	\N
4	The Roxy	1111111111	SE Stark	Portland	Oregon	97201	\N	\N
5	Tugboat	111111111111	SW Broadway	Portland	OR	97205	\N	\N
1	Original Hotcake House	1111111111	SE Powell Blvd	Portland	OR	97204	3	http://www.mikepolk.com/wp-content/uploads/2014/05/hotcake.jpg
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurants_id_seq', 6, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, user_name, score, password) FROM stdin;
1	test	\N	test
4	Mister	\N	mister
5	Duder	\N	duder
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 5, true);


--
-- Name: check_ins_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY check_ins
    ADD CONSTRAINT check_ins_pkey PRIMARY KEY (id);


--
-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: insert_into_check_ins; Type: TRIGGER; Schema: public; Owner: Guest
--

CREATE TRIGGER insert_into_check_ins BEFORE INSERT ON check_ins FOR EACH ROW EXECUTE PROCEDURE insert_into_modified_column();


--
-- Name: update_check_in; Type: TRIGGER; Schema: public; Owner: Guest
--

CREATE TRIGGER update_check_in BEFORE UPDATE ON check_ins FOR EACH ROW EXECUTE PROCEDURE update_modified_column();


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

