############################################################################
##
## Copyright (C) 2019 The Qt Company Ltd.
## Contact: https://www.qt.io/licensing/
##
## This file is part of the Boot to Qt meta layer.
##
## $QT_BEGIN_LICENSE:GPL$
## Commercial License Usage
## Licensees holding valid commercial Qt licenses may use this file in
## accordance with the commercial license agreement provided with the
## Software or, alternatively, in accordance with the terms contained in
## a written agreement between you and The Qt Company. For licensing terms
## and conditions see https://www.qt.io/terms-conditions. For further
## information use the contact form at https://www.qt.io/contact-us.
##
## GNU General Public License Usage
## Alternatively, this file may be used under the terms of the GNU
## General Public License version 3 or (at your option) any later version
## approved by the KDE Free Qt Foundation. The licenses are as published by
## the Free Software Foundation and appearing in the file LICENSE.GPL3
## included in the packaging of this file. Please review the following
## information to ensure the GNU General Public License requirements will
## be met: https://www.gnu.org/licenses/gpl-3.0.html.
##
## $QT_END_LICENSE$
##
############################################################################

DESCRIPTION = "Common default environment variables for running Qt applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += "file://defaults"

do_configure() {
    if [ "${QT_USE_SOFTWARE_CONTEXT}" ]; then
        echo "QMLSCENE_DEVICE=softwarecontext" >> ${WORKDIR}/defaults
        echo "QT_QPA_PLATFORM=linuxfb" >>  ${WORKDIR}/defaults
        echo "QSG_RENDER_LOOP=basic" >>  ${WORKDIR}/defaults
    fi
}

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/defaults ${D}${sysconfdir}/default/qt

    # loginctl enable-linger root
    install -d ${D}/var/lib/systemd/linger
    touch ${D}/var/lib/systemd/linger/root
}
