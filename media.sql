--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: arunda
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying,
    stylistid integer
);


ALTER TABLE public.clients OWNER TO arunda;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: arunda
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO arunda;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: arunda
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: arunda
--

CREATE TABLE public.stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.stylists OWNER TO arunda;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: arunda
--

CREATE SEQUENCE public.stylists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stylists_id_seq OWNER TO arunda;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: arunda
--

ALTER SEQUENCE public.stylists_id_seq OWNED BY public.stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: arunda
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: arunda
--

ALTER TABLE ONLY public.stylists ALTER COLUMN id SET DEFAULT nextval('public.stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: arunda
--

COPY public.clients (id, name, stylistid) FROM stdin;
\.


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: arunda
--

COPY public.stylists (id, name) FROM stdin;
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: arunda
--

SELECT pg_catalog.setval('public.clients_id_seq', 1, false);


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: arunda
--

SELECT pg_catalog.setval('public.stylists_id_seq', 1, false);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: arunda
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: arunda
--

ALTER TABLE ONLY public.stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

