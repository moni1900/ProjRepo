package logger

import (
	"runtime"

	"github.com/sirupsen/logrus"
)

var (
	name    string
	correlationId string
	version string
	//Setting default log level as INFO
	logLevel = INFO
)

type Pairs map[string]interface{}

//Logging levels
const (
	DEBUG = iota
	INFO
	WARN
	ERROR
	FATAL
	PANIC
)

func SetDefaultLogFormat() {
	logrus.SetFormatter(&logrus.JSONFormatter{})
}

//Init ... Function to initialize logger settings
func Init(level, format string) {
	setLogLevel(level)
	setLogFormat(format)
}

//setLogLevel ... function to set log level based on config
func setLogLevel(level string) {
	if level == "" {
		return
	}
	switch level {
	case "DEBUG":
		logLevel = DEBUG
		logrus.SetLevel(logrus.DebugLevel)
	case "INFO":
		logLevel = INFO
		logrus.SetLevel(logrus.InfoLevel)
	case "WARN":
		logLevel = WARN
		logrus.SetLevel(logrus.WarnLevel)
	case "ERROR":
		logLevel = ERROR
		logrus.SetLevel(logrus.ErrorLevel)
	case "FATAL":
		logLevel = FATAL
		logrus.SetLevel(logrus.FatalLevel)
	case "PANIC":
		logLevel = PANIC
		logrus.SetLevel(logrus.PanicLevel)
	}
}

//setLogFormat ... function to set format of logger statements
//NOTE: JSON format is also supported but for now considering only text
func setLogFormat(format string) {

	switch format {
	case "TEXT":
		logrus.SetFormatter(&logrus.TextFormatter{})
	case "JSON":
		logrus.SetFormatter(&logrus.JSONFormatter{})
	default:
		//We are going to begin using Kibana for logging, Kibana requires logs outputted as JSON to build dashboards for
		logrus.SetFormatter(&logrus.JSONFormatter{})
	}
}

//getContext ... function to fetch log line structure
func getContext() *logrus.Entry {
	pc, fileName, line, _ := runtime.Caller(3)
	funcName := runtime.FuncForPC(pc).Name()
	return logrus.WithFields(logrus.Fields{
		"name":    name,
		"version": version,
		"file":    fileName,
		"func":    funcName,
		"line":    line,
	})
}

//getContext ... function to construct log statements
func prepareContext(Pairs []Pairs) *logrus.Entry {
	contextLogger := getContext()
	for _, pair := range Pairs {
		for key, val := range pair {
			contextLogger = contextLogger.WithField(key, val)
		}
	}
	return contextLogger
}

func DebugWithTag(tag string, str string, pairs ...Pairs) {
	if logLevel > DEBUG {
		return
	}

	contextLogger := prepareContext(pairs)
	contextLogger.Debug(tag + ": " + str)
}

func InfoWithTag(tag string, str string, pairs ...Pairs) {
	if logLevel > INFO {
		return
	}
	contextLogger := prepareContext(pairs)
	contextLogger.Info(tag + ": " + str)
}

func WarnWithTag(tag string, str string, pairs ...Pairs) {
	if logLevel > WARN {
		return
	}
	contextLogger := prepareContext(pairs)
	contextLogger.Warn(tag + ": " + str)
}

func ErrorWithTag(tag string, str string, pairs ...Pairs) {
	if logLevel > ERROR {
		return
	}
	contextLogger := prepareContext(pairs)
	contextLogger.Error(tag + ": " + str)
}

//Debug ... function to construct and write Debugging log statements
func Debug(str string, Pairs ...Pairs) {
	if logLevel > DEBUG {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Debug(str)
}

//Info ... function to construct and write Information log statements
func Info(str string, Pairs ...Pairs) {
	if logLevel > INFO {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Info(str)
}

//Warn ... function to construct and write Warning log statements
func Warn(str string, Pairs ...Pairs) {
	if logLevel > WARN {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Warn(str)
}

//Error ... function to construct and write Error log statements
func Error(str string, Pairs ...Pairs) {
	if logLevel > ERROR {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Error(str)
}

//Fatal ... function to construct and write Fatal log statements
func Fatal(str string, Pairs ...Pairs) {
	if logLevel > FATAL {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Fatal(str)
}

//Panic ... function to construct and write Panic log statements
func Panic(str string, Pairs ...Pairs) {
	if logLevel > PANIC {
		return
	}
	contextLogger := prepareContext(Pairs)
	contextLogger.Panic(str)
}
