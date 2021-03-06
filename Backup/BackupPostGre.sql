PGDMP                      
    t           controledeponto    9.3.5    9.3.5 0    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    17737    controledeponto    DATABASE     �   CREATE DATABASE controledeponto WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8';
    DROP DATABASE controledeponto;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6            �            3079    11791    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    180            �            1259    17740    cargahoraria    TABLE     �   CREATE TABLE cargahoraria (
    idcargahoraria integer NOT NULL,
    horainicio time without time zone NOT NULL,
    horafim time without time zone NOT NULL,
    intervalo time without time zone NOT NULL,
    fkfuncionario integer NOT NULL
);
     DROP TABLE public.cargahoraria;
       public         postgres    false    6            �            1259    17743    cargahoraria_idcargahoraria_seq    SEQUENCE     �   CREATE SEQUENCE cargahoraria_idcargahoraria_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.cargahoraria_idcargahoraria_seq;
       public       postgres    false    6    170            �           0    0    cargahoraria_idcargahoraria_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE cargahoraria_idcargahoraria_seq OWNED BY cargahoraria.idcargahoraria;
            public       postgres    false    171            �            1259    17745    cartaodepontos    TABLE     J  CREATE TABLE cartaodepontos (
    idcartaodeponto integer NOT NULL,
    fkfuncionario integer NOT NULL,
    data date NOT NULL,
    iniciodiario time without time zone NOT NULL,
    iniciointervalo time without time zone NOT NULL,
    fimintervalo time without time zone NOT NULL,
    fimdiario time without time zone NOT NULL
);
 "   DROP TABLE public.cartaodepontos;
       public         postgres    false    6            �            1259    17748 "   cartaodepontos_idcartaodeponto_seq    SEQUENCE     �   CREATE SEQUENCE cartaodepontos_idcartaodeponto_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.cartaodepontos_idcartaodeponto_seq;
       public       postgres    false    172    6            �           0    0 "   cartaodepontos_idcartaodeponto_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE cartaodepontos_idcartaodeponto_seq OWNED BY cartaodepontos.idcartaodeponto;
            public       postgres    false    173            �            1259    17810    configbackup    TABLE     �   CREATE TABLE configbackup (
    idconfigbackup integer NOT NULL,
    localsgdb character varying(255),
    localbkp character varying(255),
    localrest character varying(255)
);
     DROP TABLE public.configbackup;
       public         postgres    false    6            �            1259    17808    configbackup_idconfigbackup_seq    SEQUENCE     �   CREATE SEQUENCE configbackup_idconfigbackup_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.configbackup_idconfigbackup_seq;
       public       postgres    false    179    6            �           0    0    configbackup_idconfigbackup_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE configbackup_idconfigbackup_seq OWNED BY configbackup.idconfigbackup;
            public       postgres    false    178            �            1259    17757 	   enderecos    TABLE     K  CREATE TABLE enderecos (
    idendereco integer NOT NULL,
    logradouro character varying(255) NOT NULL,
    numero character varying(10) DEFAULT 'S/N'::character varying,
    bairro character varying(80),
    cidade character varying(150),
    cep character varying(10),
    uf character varying(2),
    fkfuncionario integer
);
    DROP TABLE public.enderecos;
       public         postgres    false    6            �            1259    17764    enderecos_idendereco_seq    SEQUENCE     y   CREATE SEQUENCE enderecos_idendereco_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.enderecos_idendereco_seq;
       public       postgres    false    6    174            �           0    0    enderecos_idendereco_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE enderecos_idendereco_seq OWNED BY enderecos.idendereco;
            public       postgres    false    175            �            1259    17766    funcionarios    TABLE     �  CREATE TABLE funcionarios (
    idfuncionario integer NOT NULL,
    nome character varying(255) NOT NULL,
    sexo character(1),
    cpf character varying(14) NOT NULL,
    rg character varying(20),
    datanascimento date NOT NULL,
    nomepai character varying(255),
    nomemae character varying(255),
    cpts character varying(30),
    telefonecelular character varying(15),
    telefonefixo character varying(15),
    email character varying(255)
);
     DROP TABLE public.funcionarios;
       public         postgres    false    6            �            1259    17772    funcionarios_idfuncionario_seq    SEQUENCE        CREATE SEQUENCE funcionarios_idfuncionario_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.funcionarios_idfuncionario_seq;
       public       postgres    false    176    6            �           0    0    funcionarios_idfuncionario_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE funcionarios_idfuncionario_seq OWNED BY funcionarios.idfuncionario;
            public       postgres    false    177            b           2604    17774    idcargahoraria    DEFAULT     |   ALTER TABLE ONLY cargahoraria ALTER COLUMN idcargahoraria SET DEFAULT nextval('cargahoraria_idcargahoraria_seq'::regclass);
 J   ALTER TABLE public.cargahoraria ALTER COLUMN idcargahoraria DROP DEFAULT;
       public       postgres    false    171    170            c           2604    17775    idcartaodeponto    DEFAULT     �   ALTER TABLE ONLY cartaodepontos ALTER COLUMN idcartaodeponto SET DEFAULT nextval('cartaodepontos_idcartaodeponto_seq'::regclass);
 M   ALTER TABLE public.cartaodepontos ALTER COLUMN idcartaodeponto DROP DEFAULT;
       public       postgres    false    173    172            g           2604    17813    idconfigbackup    DEFAULT     |   ALTER TABLE ONLY configbackup ALTER COLUMN idconfigbackup SET DEFAULT nextval('configbackup_idconfigbackup_seq'::regclass);
 J   ALTER TABLE public.configbackup ALTER COLUMN idconfigbackup DROP DEFAULT;
       public       postgres    false    178    179    179            e           2604    17777 
   idendereco    DEFAULT     n   ALTER TABLE ONLY enderecos ALTER COLUMN idendereco SET DEFAULT nextval('enderecos_idendereco_seq'::regclass);
 C   ALTER TABLE public.enderecos ALTER COLUMN idendereco DROP DEFAULT;
       public       postgres    false    175    174            f           2604    17778    idfuncionario    DEFAULT     z   ALTER TABLE ONLY funcionarios ALTER COLUMN idfuncionario SET DEFAULT nextval('funcionarios_idfuncionario_seq'::regclass);
 I   ALTER TABLE public.funcionarios ALTER COLUMN idfuncionario DROP DEFAULT;
       public       postgres    false    177    176            �          0    17740    cargahoraria 
   TABLE DATA               ^   COPY cargahoraria (idcargahoraria, horainicio, horafim, intervalo, fkfuncionario) FROM stdin;
    public       postgres    false    170   9       �           0    0    cargahoraria_idcargahoraria_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('cargahoraria_idcargahoraria_seq', 4, true);
            public       postgres    false    171            �          0    17745    cartaodepontos 
   TABLE DATA                  COPY cartaodepontos (idcartaodeponto, fkfuncionario, data, iniciodiario, iniciointervalo, fimintervalo, fimdiario) FROM stdin;
    public       postgres    false    172   b9       �           0    0 "   cartaodepontos_idcartaodeponto_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('cartaodepontos_idcartaodeponto_seq', 0, true);
            public       postgres    false    173            �          0    17810    configbackup 
   TABLE DATA               O   COPY configbackup (idconfigbackup, localsgdb, localbkp, localrest) FROM stdin;
    public       postgres    false    179   9       �           0    0    configbackup_idconfigbackup_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('configbackup_idconfigbackup_seq', 1, true);
            public       postgres    false    178            �          0    17757 	   enderecos 
   TABLE DATA               d   COPY enderecos (idendereco, logradouro, numero, bairro, cidade, cep, uf, fkfuncionario) FROM stdin;
    public       postgres    false    174   �9       �           0    0    enderecos_idendereco_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('enderecos_idendereco_seq', 4, true);
            public       postgres    false    175            �          0    17766    funcionarios 
   TABLE DATA               �   COPY funcionarios (idfuncionario, nome, sexo, cpf, rg, datanascimento, nomepai, nomemae, cpts, telefonecelular, telefonefixo, email) FROM stdin;
    public       postgres    false    176   U:       �           0    0    funcionarios_idfuncionario_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('funcionarios_idfuncionario_seq', 4, true);
            public       postgres    false    177            i           2606    17780    cargahoraria_fkfuncionario_key 
   CONSTRAINT     h   ALTER TABLE ONLY cargahoraria
    ADD CONSTRAINT cargahoraria_fkfuncionario_key UNIQUE (fkfuncionario);
 U   ALTER TABLE ONLY public.cargahoraria DROP CONSTRAINT cargahoraria_fkfuncionario_key;
       public         postgres    false    170    170            k           2606    17782    cargahoraria_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY cargahoraria
    ADD CONSTRAINT cargahoraria_pkey PRIMARY KEY (idcargahoraria);
 H   ALTER TABLE ONLY public.cargahoraria DROP CONSTRAINT cargahoraria_pkey;
       public         postgres    false    170    170            m           2606    17784    cartaodepontos_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY cartaodepontos
    ADD CONSTRAINT cartaodepontos_pkey PRIMARY KEY (idcartaodeponto);
 L   ALTER TABLE ONLY public.cartaodepontos DROP CONSTRAINT cartaodepontos_pkey;
       public         postgres    false    172    172            u           2606    17818    configbackup_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY configbackup
    ADD CONSTRAINT configbackup_pkey PRIMARY KEY (idconfigbackup);
 H   ALTER TABLE ONLY public.configbackup DROP CONSTRAINT configbackup_pkey;
       public         postgres    false    179    179            o           2606    17788    enderecos_fkfuncionario_key 
   CONSTRAINT     b   ALTER TABLE ONLY enderecos
    ADD CONSTRAINT enderecos_fkfuncionario_key UNIQUE (fkfuncionario);
 O   ALTER TABLE ONLY public.enderecos DROP CONSTRAINT enderecos_fkfuncionario_key;
       public         postgres    false    174    174            q           2606    17790    enderecos_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY enderecos
    ADD CONSTRAINT enderecos_pkey PRIMARY KEY (idendereco);
 B   ALTER TABLE ONLY public.enderecos DROP CONSTRAINT enderecos_pkey;
       public         postgres    false    174    174            s           2606    17792    funcionarios_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY funcionarios
    ADD CONSTRAINT funcionarios_pkey PRIMARY KEY (idfuncionario);
 H   ALTER TABLE ONLY public.funcionarios DROP CONSTRAINT funcionarios_pkey;
       public         postgres    false    176    176            v           2606    17793    cargahoraria_fkfuncionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY cargahoraria
    ADD CONSTRAINT cargahoraria_fkfuncionario_fkey FOREIGN KEY (fkfuncionario) REFERENCES funcionarios(idfuncionario);
 V   ALTER TABLE ONLY public.cargahoraria DROP CONSTRAINT cargahoraria_fkfuncionario_fkey;
       public       postgres    false    176    170    1907            w           2606    17798 !   cartaodepontos_fkfuncionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY cartaodepontos
    ADD CONSTRAINT cartaodepontos_fkfuncionario_fkey FOREIGN KEY (fkfuncionario) REFERENCES funcionarios(idfuncionario);
 Z   ALTER TABLE ONLY public.cartaodepontos DROP CONSTRAINT cartaodepontos_fkfuncionario_fkey;
       public       postgres    false    172    176    1907            x           2606    17803    enderecos_fkfuncionario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY enderecos
    ADD CONSTRAINT enderecos_fkfuncionario_fkey FOREIGN KEY (fkfuncionario) REFERENCES funcionarios(idfuncionario);
 P   ALTER TABLE ONLY public.enderecos DROP CONSTRAINT enderecos_fkfuncionario_fkey;
       public       postgres    false    176    1907    174            �   6   x�3�40�20 "NC����"�e��6@��0�����0��L�I�p��qqq �{'      �      x������ � �      �      x�3�,I-.IE&�b���� jrn      �   �   x���;�0��>E.@��U��7D%HM��.Uo��Ą8Q.F�s���-��lEq��y1[�fB#^D8�T���q�����)�����j[���H)i�Iq�Z6昍p�:��+.?�CL7�=�]rM!"���y0?�]�B{R�{��.�o��5/3���B�      �   Y  x����N�0���S�X��6	�mA��N]#��&.Hch�Hl��h�n�τ9�����q���\�y��E^әy����8͔"� �E��0!I�.��&
G����	Y^��-�T$Nͅ��X�!Nu�گ��]��ԍ�ݶ]o�m��R����UY@N�+|��*GX2&Z��Jm�4DK�U$������rq���C�$��Ph6�Z�6�s�Ƙ��L���<TX�e����澧�2�H����	������s�  �Ӊ�z@�u��6ŒN�z����e�&R�DµJ�`"9��`���>7���j��k_����E��j��     