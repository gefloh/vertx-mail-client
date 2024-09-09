/*
 *  Copyright (c) 2011-2015 The original author or authors
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *       The Eclipse Public License is available at
 *       http://www.eclipse.org/legal/epl-v10.html
 *
 *       The Apache License v2.0 is available at
 *       http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.tests.mail.internal;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.mail.MailResult;
import io.vertx.ext.mail.impl.MailClientImpl;
import io.vertx.ext.mail.impl.SMTPConnectionPool;

/**
 * MailClient providing a few internal getters for unit tests
 *
 * @author <a href="http://oss.lehmann.cx/">Alexander Lehmann</a>
 */
public class TestMailClient implements MailClient {

  private final MailClientImpl mailClient;

  /**
   * @param vertx
   * @param config
   */
  public TestMailClient(Vertx vertx, MailConfig config) {
    mailClient = new MailClientImpl(vertx, config, "foo");
  }

  @Override
  public Future<MailResult> sendMail(MailMessage email) {
    return mailClient.sendMail(email);
  }

  @Override
  public Future<Void> close() {
    return mailClient.close();
  }

  /**
   * get the connection pool to be able to assert things about the connections
   * @return SMTPConnectionPool
   */
  public SMTPConnectionPool getConnectionPool() {
    return mailClient.getConnectionPool();
  }

  /**
   * get the connection count of the pool
   * @return SMTPConnectionPool
   */
  public int connCount() {
    return mailClient.getConnectionPool().connCount();
  }
}
