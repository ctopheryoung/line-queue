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
8	3	1	1	2016-03-11 08:15:42.532201
9	4	1	1	2016-03-11 08:27:45.804899
10	10	1	4	2016-03-11 08:47:03.206709
11	16	1	13	2016-03-11 09:51:15.302101
\.


--
-- Name: check_ins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('check_ins_id_seq', 11, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurants (id, restaurant_name, phone, street, city, state, zip, line_average, photo) FROM stdin;
5	Tugboat	(503) 226-2508	711 SW Ankeny St.	Portland	OR	97205	\N	http://guideimg.alibaba.com/images/trip/1/03/18/4/tugboat-brewing-company_173294.jpg
12	Yamhill Pub	(503) 295-6613	223 SW Yamhill St.	Portland	OR	97204	\N	\N
10	Goose Hollow Inn	(503) 228-7010	1927 SW Jefferson St. 	Portland	OR	97201	\N	http://media1.fdncms.com/portmerc/imager/goose-hollow-inn/u/original/842510/goose_hollow_inn.jpg
9	Shigezo	(503) 688-5202	910 SW Salmon St.	Portland	OR	97205	\N	http://assets3.thrillist.com/v1/image/1471568/size/tl-horizontal_main/shigezo-izakaya
11	¿Por Que No?	(503) 954-3138	4635 SE Hawthorne Blvd.	Portland	OR	97215	\N	http://www.bringfido.com/site_media/images/thumbs/por_que-portland.jpg
15	Taste Tickler	(503) 282-3681	1704 NE 14th Ave.	Portland	OR	97212	\N	http://2.bp.blogspot.com/-Wa9ceicbuIk/UAhKAtMVwhI/AAAAAAAABc8/pp75KXUi--Q/s1600/DSC_0615_2.JPG
8	Urban Farmer	(503) 222-4900	525 SW Morrison St.	Portland	OR	97204	\N	https://lh4.googleusercontent.com/-5q_d17ukBKQ/T4w93ECJ1YI/AAAAAAAAAIE/YzGfCXdSEr0/s0/The%2BNines%2B-%2BURBAN%2BFARMER%2B1.jpg
13	Smokehouse 21	(971) 373-8990	413 NW 21st Ave.	Portland	OR	97209	\N	http://www.neighborhoodnotes.com/uploads/images/2014-10-21-smokehouse-21-happy-hour-011_2_3-photo.jpg
14	Broadway Grill & Brewery	(503) 284 4460	1700 NE Broadway St.	Portland	OR	97232	\N	http://2.bp.blogspot.com/-Z7FAd8PJ5fM/T3iampKd_3I/AAAAAAAAB6U/NX_84FuIy9M/s1600/brdwy%2Bpbr.JPG
16	Little Big Burger (NE)	(503) 265-8781	3747 N Mississippi Ave	Portland	OR	97227	\N	https://cdn0.vox-cdn.com/thumbor/1DL9ZXP2Pcy54Pbgb1_IIDU1kC0=/0x50:1000x613/1600x900/cdn0.vox-cdn.com/uploads/chorus_image/image/38790598/little-big-burger-ext-division.0.jpg
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurants_id_seq', 18, true);


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

