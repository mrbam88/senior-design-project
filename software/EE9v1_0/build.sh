#!/bin/sh
# Compile EE9 on a modern JDK (tested: Temurin 26). No ant needed.
set -e
cd "$(dirname "$0")"
mkdir -p build/classes compat/classes
# Shim for sun.misc.Compare/Sort, removed from the JDK; used by lib/modiaf.jar (INGENIAS).
javac --patch-module jdk.unsupported=compat/src -d compat/classes compat/src/sun/misc/*.java
find Package -name '*.java' > build/sources.txt
javac -nowarn -encoding ISO-8859-1 -cp "lib/*" -d build/classes @build/sources.txt
echo "build ok"
