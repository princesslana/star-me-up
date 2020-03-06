# Star Me Up
A really simple starboard bot for Discord.

Built on top of the SmallD Discord library (https://github.com/princesslana/smalld)

## Features/Limitations

* Minimal configuration.
* No database setup required.
* Single guild only.
* Messages are added to Starboard after being starred once.

## Running

To run star me up you'lll need java and maven installed.

The following configuration is required via enviornment variables:

* **SMU_TOKEN** The Discord bot token
* **SMU_CHANNEL_ID** The id of the channel that starred messages will be posted to

An example of building and running the bot locally may look like:
```
  > mvn package
  > SMU_TOKEN=<token_here> SMU_CHANNEL_ID=<channel_id_here> java -jar target/starmeup.jar
```

## Contributing

To contribute, please make a PR and submit it.
It will be reviewed when possible.

You may wish to discuss possible features and how to add them before starting work.
To do so please contact via details below.

## Contact

Via Discord, at The Programmer's Hangout (https://discord.gg/programming).
Look for Princess Lana (Lana#4231).
