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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: check_ins; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE check_ins (
    id integer NOT NULL,
    restaurant_id integer,
    user_id integer,
    check_in timestamp without time zone,
    in_line boolean,
    line_length integer
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
    password character varying,
    permission character varying
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

COPY check_ins (id, restaurant_id, user_id, check_in, in_line, line_length) FROM stdin;
\.


--
-- Name: check_ins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('check_ins_id_seq', 1, false);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurants (id, restaurant_name, phone, street, city, state, zip, line_average, photo) FROM stdin;
1	restaurant	1111111111	11 street	portland	or	97204	3	\N
2	restaurant	1111111111	11 street	portland	or	97204	3	\N
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurants_id_seq', 2, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, user_name, score, password, permission) FROM stdin;
1	test	\N	test	\N
2	test	\N	test	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 2, true);


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
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

