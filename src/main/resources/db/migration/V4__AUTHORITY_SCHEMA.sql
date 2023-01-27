CREATE TABLE authority (
    id bigint NOT NULL PRIMARY KEY ,
    authority VARCHAR NOT NULL,
    CONSTRAINT usuario_id FOREIGN KEY (id)
    REFERENCES usuario(id)
)