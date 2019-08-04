const express = require("express");
const multer = require("multer");
const path = require("path");
const uuidv4 = require("uuid/v4");

const UPLOAD_URL = "uploads";

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    /*
            Files will be saved in the 'uploads' directory. Make
            sure this directory already exists!
          */
    cb(null, "./uploads");
  },
  filename: (req, file, cb) => {
    /*
            uuidv4() will generate a random ID that we'll use for the
            new filename. We use path.extname() to get
            the extension from the original file name and add that to the new
            generated ID. These combined will create the file name used
            to save the file on the server and will be available as
            req.file.pathname in the router handler.
          */
    const newFilename = `${uuidv4()}${path.extname(file.originalname)}`;
    cb(null, newFilename);
  }
});

// create the multer instance that will be used to upload/save the file
const upload = multer({ storage });

var app = express();
app.use("/uploads", express.static("uploads"));

app.post("/upload", upload.single("file"), function(req, res, next) {
  // req.file is the `avatar` file
  // req.body will hold the text fields, if there were any
  //const newFileName = `${uuidv4()}${path.extname(req.file.originalname)}`;
  const url = UPLOAD_URL + '/' + req.file.filename;
  res.send({ url });
});

app.listen(5050);
