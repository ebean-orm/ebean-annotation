package io.ebean.annotation;

/**
 * Built in supported platforms.
 */
public enum Platform {

  /**
   * Generic platform configured via properties or code.
   */
  GENERIC,

  /**
   * DB2.
   */
  DB2,

  /**
   * CockroachDB.
   */
  COCKROACH,

  /**
   * ClickHouse.
   */
  CLICKHOUSE,

  /**
   * H2.
   */
  H2,

  /**
   * HsqlDB.
   */
  HSQLDB,

  /**
   * Postgres.
   */
  POSTGRES,

  /**
   * MySql.
   */
  MYSQL,

  /**
   * MySql 5.5.
   */
  MYSQL55(MYSQL),

  /**
   * NuoDB.
   */
  NUODB,

  /**
   * Oracle.
   */
  ORACLE,

  /**
   * SQLAnywhere.
   */
  SQLANYWHERE,

  /**
   * SQLite.
   */
  SQLITE,

  /**
   * Microsoft SQL Server (preferred platform 2017).
   */
  SQLSERVER,

  /**
   * Microsoft SQL Server 2016 platform (Non UTF8 types and Identity by default).
   */
  SQLSERVER16(SQLSERVER),

  /**
   * Microsoft SQL Server 2017 platform (UTF8 types and Sequence).
   */
  SQLSERVER17(SQLSERVER),

  /**
   * SAP HANA
   */
  HANA;

  private Platform base;

  Platform() {
    this.base = this;
  }

  Platform(Platform base) {
    this.base = base;
  }

  /**
   * Return the base platform.
   */
  public Platform base() {
    return base;
  }
}
