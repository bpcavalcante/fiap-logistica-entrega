CREATE TABLE IF NOT EXISTS entrega
(
    `id_entrega`        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_pedido`         BIGINT(20) UNSIGNED NOT NULL,
    `latitude`          DECIMAL(9,6) NOT NULL,
    `longitude`         DECIMAL(9,6) NOT NULL,
    `cep`               VARCHAR(10) NOT NULL,
    `date_created`      DATETIME NOT NULL,
    `date_last_updated` DATETIME,
    PRIMARY KEY (`id_entrega`)
);