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

package io.vertx.tests.mail.internal.sasl;

import io.vertx.ext.mail.impl.sasl.AuthOperation;
import io.vertx.ext.mail.impl.sasl.AuthXOAUTH2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthXOAUTH2Test {
  @Test
  public void testAuth() {
    AuthOperation result = new AuthXOAUTH2("xxx", "yyy");
    assertNotNull(result);
    assertEquals("XOAUTH2", result.getName());
  }

  @Test
  public void testGetName() {
    assertEquals("XOAUTH2", new AuthXOAUTH2("xxx", "yyy").getName());
  }

  @Test
  public void testNextStep() {
    final AuthOperation auth = new AuthXOAUTH2("xxx", "yyy");
    assertEquals("user=xxx\1auth=Bearer yyy\1\1", auth.nextStep(null));
    assertEquals(null, auth.nextStep("235 2.7.0 Accepted"));
  }

  @Test
  public void testNextStepError() {
    final AuthOperation auth = new AuthXOAUTH2("xxx", "yyy");
    assertEquals("user=xxx\1auth=Bearer yyy\1\1", auth.nextStep(null));
    assertEquals("", auth.nextStep("{\"status\":\"401\",\"schemes\":\"bearer\",\"scope\":\"https://mail.google.com/\"}"));
  }

}
