#!/bin/zsh

# This scrypt make from mac os

#colors
RED_COLOR="\e[31m"
GREEN_COLOR="\e[32m"
BROWN_COLOR="\e[33m"
BLUE_COLOR="\e[34m"
RESET_COLOR="\e[0m"

MAIN_CLASS=com.school21.ttanja.smartcalc.StockUiApplication

FILE=Calc
TYPE=pkg
TARGET_PATH=.
ICON=./images/SmartCalc_v3.0.icns
JAR_FILE=smartCalc-0.0.1-SNAPSHOT.jar
APP_VERSION="3"


if [ -f "$FILE-$APP_VERSION.$TYPE" ]; then
  rm "$FILE-$APP_VERSION.$TYPE"
  echo -e "$BLUE_COLOR""Old package $RED_COLOR$FILE-$APP_VERSION.$TYPE$BLUE_COLOR removed $RESET_COLOR"
fi

java -version

echo -e "$GREEN_COLOR""Start build package $RESET_COLOR"

#java -jar ../../../target/$JAR_FILE

jpackage -t $TYPE \
  --input $TARGET_PATH \
  --name "$FILE" \
  --main-jar ../../../target/$JAR_FILE \
  --main-class $MAIN_CLASS \
  --icon $ICON \
  --mac-package-identifier StockUiApplication \
  --mac-package-name "Smart Calc v3" \
  --app-version 3 \
  --vendor "student ttanja"


echo -e "$GREEN_COLOR""Package $BROWN_COLOR$FILE-$APP_VERSION.$TYPE$GREEN_COLOR built$RESET_COLOR"