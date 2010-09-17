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

package net.sf.hibernate.jconsole.ui.widgets;

import static net.sf.hibernate.jconsole.ui.widgets.AbstractJTable.round;

/**
 * Implements a table cell that shows the (cache) hit rate.
 *
 * @author Juergen_Kellerer, 2009-11-20
 * @version 1.0
 */
public class HitrateTableCell extends LineBarTableCell {

	public static double toHitRate(long hitCount, long missCount) {
		if (hitCount == 0 && missCount == 0)
			return 0;
		return (double) hitCount / (double) (missCount + hitCount);
	}

	public HitrateTableCell(long hitCount, long missCount, long putCount) {
		double hitRate = toHitRate(hitCount, missCount);

		setValue(hitRate);
		setLabelValue(round(hitRate * 100D));
		setLabelFormat("%2.2f%%");

		setToolTipText(String.format("hits: %d, misses: %d, puts: %d", hitCount, missCount, putCount));
	}
}
