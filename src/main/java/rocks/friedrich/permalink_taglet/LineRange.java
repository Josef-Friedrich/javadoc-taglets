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

/**
 * Represents a range of lines in a file.
 *
 * <p>
 * The LineRange class provides methods to retrieve the beginning and end line
 * numbers of the range, as well as generate an HTML representation of the
 * range.
 * </p>
 *
 * @author Josef Friedrich
 */
public class LineRange
{
    private final int begin;

    private final int end;

    public LineRange(String query)
    {
        String[] segments = query.split("-");
        int[] lines = new int[segments.length];
        for (int i = 0; i < segments.length; i++)
        {
            String lineNumber = segments[i];
            lineNumber = lineNumber.replace("L", "");
            lines[i] = Integer.parseInt(lineNumber);
        }
        begin = lines[0];
        if (segments.length == 1)
        {
            end = -1;
        }
        else
        {
            end = lines[1];
        }
    }

    /**
     * Returns the beginning line number.
     *
     * @return the beginning line number
     */
    public int getBegin()
    {
        return begin;
    }

    /**
     * Returns the end line number.
     *
     * @return the end line number.
     */
    public int getEnd()
    {
        return end;
    }

    /**
     * @hidden
     */
    public String toString()
    {
        if (end == -1)
        {
            return "Line " + begin;
        }
        else
        {
            return "Lines " + begin + " - " + end;
        }
    }
}
