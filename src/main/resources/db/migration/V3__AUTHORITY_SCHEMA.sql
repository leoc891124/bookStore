CREATE TABLE authority (
    id bigint NOT NULL AUTO_INCREMENT,
    authid uuid NOT NULL PRIMARY KEY,
    authority VARCHAR NOT NULL,
    CONSTRAINT authid FOREIGN KEY (authid) REFERENCES usuario(id)


)

