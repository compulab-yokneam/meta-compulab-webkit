LICENSE = "MIT & Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818 \
                    file://tinywl/LICENSE;md5=d957da0415f5b0b974bfc6063afab2b5"

BRANCH = "0.16"
SRC_URI = "git://gitlab.freedesktop.org/wlroots/wlroots.git;protocol=https;branch=${BRANCH}"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "0a32b5a74db06a27bee55a47205951bb277a9657"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

DEPENDS = " \
    wayland \
    wayland-native \
    wayland-protocols \
    libdrm \
    libinih \
    pipewire \
    virtual/libgbm \
    libxkbcommon \
    pixman \
    hwdata \
    seatd \
"

inherit meson pkgconfig features_check
REQUIRED_DISTRO_FEATURES = "opengl wayland"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'basu', d)}"

PACKAGECONFIG[basu] = "-Dsd-bus-provider=basu,,basu"

FILES:${PN} += "${systemd_user_unitdir} ${datadir}"

