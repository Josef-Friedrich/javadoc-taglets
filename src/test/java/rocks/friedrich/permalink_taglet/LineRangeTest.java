/*
 * Copyright (c) 2024 Josef Friedrich.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package rocks.friedrich.permalink_taglet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineRangeTest
{
    @Test
    void testBeginAndEnd()
    {
        LineRange range = new LineRange("L1-L2");
        assertEquals(range.getBegin(), 1);
        assertEquals(range.getEnd(), 2);
    }

    @Test
    void testBeginOnly()
    {
        LineRange range = new LineRange("L123");
        assertEquals(range.getBegin(), 123);
        assertEquals(range.getEnd(), -1);
    }

    @Test
    void testToString()
    {
        LineRange range = new LineRange("L1-L2");
        assertEquals(range.toString(), "Lines 1 - 2");
    }
}
