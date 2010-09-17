/*
 * Copyright (c) 2009
 *
 * This file is part of HibernateJConsole.
 *
 *     HibernateJConsole is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     HibernateJConsole is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with HibernateJConsole.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.hibernate.jconsole;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Regression Test for the Classpath filter
 *
 * @author Juergen_Kellerer, 01.12.2009 16:26:43
 */
public class JConsolePluginHibernateFilterTest {

	private void assertAccepts(File file) {
		assertTrue("Did not accept: " + file,
				JConsolePlugin.HIBERNATE_FILTER.accept(file.getParentFile(), file.getName()));
	}

	private void assertDenies(File file) {
		assertFalse("Did accept: " + file,
				JConsolePlugin.HIBERNATE_FILTER.accept(file.getParentFile(), file.getName()));
	}

	@Test
	public void testFilterAcceptsJarsWithHibernateKeyword() {
		for (String name : Arrays.asList("hibernate.jar",
				"prefix-hibernate.jar", "hibernate-postfix.jar",
				"hibernate3.jar", "hibernate4.jar", "hibernate-core.jar"))
			assertAccepts(new File(name));
	}

	@Test
	public void testFilterAcceptsDirectories() {
		File f = new File(".");
		assertTrue(f.isDirectory());
		assertAccepts(f);
	}

	@Test
	public void testFilterDeniesJarsWithNoHibernateKeyword() {
		for (String name : Arrays.asList("ant.jar",
				"something.jar", "junit.jar", ".jar"))
			assertDenies(new File(name));
	}

	@Test
	public void testFilterDeniesHibernateKeywordOnly() {
		assertDenies(new File("hibernate"));
	}

	@Test
	public void testFilterDeniesSelfNamedJar() {
		for (String name : Arrays.asList("hibernate-jconsole.jar",
				"hibernate-jconsole-1.0.jar", "hibernate-jconsole.x.jar"))
			assertDenies(new File(name));
	}
}
