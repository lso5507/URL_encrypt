REPOSITORY=/home/ec2-user/app/deploy
PROJECT_NAME=springboot2-webservice



echo "> 현재 구동 중인 애플리케이션 pid 확인"

# 실행 중이면 종료하기 위해서 현재 수행 중인 프로세스id를 찾습니다.
# springboot2-webservice으로 된 다른 프로그램들이 있을 수 있어 springboot2-webservice된 jar 프로세스를 찾은 뒤 id를 찾습니다(awk '{print $1}').
CURRENT_PID=$(ps -ef | grep url-enc | grep -v grep | awk '{print $2}' )

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR name: $JAR_NAME"
echo "> $JAR_NAME에 실행 권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &