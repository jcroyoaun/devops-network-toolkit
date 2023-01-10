const { spawn } = require('child_process');

const hostname = process.argv[2];

const ping = spawn('ping', [hostname]);

ping.stdout.on('data', (data) => {
  console.log(`stdout: ${data}`);
});

ping.stderr.on('data', (data) => {
  console.error(`stderr: ${data}`);
});

ping.on('close', (code) => {
  console.log(`child process exited with code ${code}`);
});
