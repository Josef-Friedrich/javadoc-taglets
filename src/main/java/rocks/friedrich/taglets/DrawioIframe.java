/*
 * Copyright (c) 2025 Josef Friedrich.
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
package rocks.friedrich.taglets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

import java.net.URLEncoder;

public class DrawioIframe
{
    private String url;

    public DrawioIframe(String url)
    {
        this.url = requireNonNull(url, "url must not be null");
    }

    private String encodeUrl()
    {
        return URLEncoder.encode(url, UTF_8);
    }

    public String toString()
    {
        return "<iframe frameborder=\"0\" src=\"https://app.diagrams.net/?lightbox=1&edit=_blank#U"
                + encodeUrl() + "\"></iframe>";
    }
}
