#!/bin/bash

INPUT="long.csv"
OUTPUT="output.csv"

while [ $# -ge 1 ]; do
	key="$1"
	shift

	case $key in
		-i|--input)
			INPUT="$1"
			shift
			;;
		-o|--output)
			OUTPUT="$1"
			shift
			;;
		*)
			echo error unknown arg: $key
			exit 1
			;;
	esac
done

#example run code:
#run abcd.language $INPUT $OUTPUT
#java -jar $INPUT $OUTPUT

# To Clean and Compile
#sbt clean compile

# To Run Unit Tests
#sbt test

sbt "run $INPUT $OUTPUT"