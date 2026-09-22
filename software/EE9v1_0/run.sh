#!/bin/sh
# Launch the epidemic simulation. Opens the JADE RMA window and the agent window.
# Close the GUI window to exit. Agent history is written to ./data.txt
cd "$(dirname "$0")"
[ -d build/classes ] || ./build.sh
exec java --patch-module jdk.unsupported=compat/classes -cp "build/classes:lib/*" ingenias.jade.RunSimulation
